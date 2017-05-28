package com.contec.rmj.bluetooth.message;

public class MessagePack {

	private int[] mOrder, mPack;
	private int[] m = { 0x00, 0x00, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40 };

	public MessagePack() {
		mOrder = new int[9];
		mPack = new int[9];
	}

	public byte[] encode(byte[] order) {
		mPack[0] = order[0];
		mPack[1] = 0x80;

		for (int i = 2; i < 9; i++) {
			mPack[1] |= (order[i] & 0x80) >> (9 - i);
			mPack[i] = order[i] | 0x80;
		}

		return ints2bytes(mPack);
	}

	public byte[] decode(byte[] pack) {
		mOrder[0] = pack[0];
		mOrder[1] = pack[1];

		for (int i = 2; i < 9; i++) {
			if((pack[1] & m[i])>0) mOrder[i] = pack[i];
			else mOrder[i] = pack[i] & 0x7f;
		}

		return ints2bytes(mOrder);
	}
	
	private byte[] ints2bytes(int[] pArray) {
		byte[] _bArray = new byte[9];
		for (int i = 0; i < 9; i++) {
			_bArray[i] = (byte) pArray[i];
		}
		return _bArray;
	}

}
