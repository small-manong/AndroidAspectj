//��һ��
/**��ȡ����(ArrayList<NameValuePair> nameValuePairs,String url)��post��Զ�̷�����
 * ����õķ��ؽ��(String)���ظ�������
 * �����������ڲ�ѯ�������ٵ�ʱ��
 * Chen.Zhidong
 * 2011-02-15*/
public String posturl(ArrayList<NameValuePair> nameValuePairs,String url){
    String result = "";
    String tmp= "";
    InputStream is = null;
    try{
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        is = entity.getContent();
    }catch(Exception e){
        return "Fail to establish http connection!";
    }
 
    try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
 
        tmp=sb.toString();
    }catch(Exception e){
        return "Fail to convert net stream!";
    }
 
    try{
        JSONArray jArray = new JSONArray(tmp);
        for(int i=0;i<jArray.length();i++){
            JSONObject json_data = jArray.getJSONObject(i);
            Iterator<?> keys=json_data.keys();
            while(keys.hasNext()){
                result += json_data.getString(keys.next().toString());
            }
        }
    }catch(JSONException e){
        return "The URL you post is wrong!";
    }
 
    return result;
}
 
//�ڶ���
/**��ȡ����ָ������ҳ���룬���䷵�ظ������ߣ��ɵ����߶������
 * ����String
 * Chen.Zhidong
 * 2011-02-15*/
public String posturl(String url){
    InputStream is = null;
    String result = "";
 
    try{
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        is = entity.getContent();
    }catch(Exception e){
        return "Fail to establish http connection!"+e.toString();
    }
 
    try{
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        is.close();
 
        result=sb.toString();
    }catch(Exception e){
        return "Fail to convert net stream!";
    }
 
    return result;
}
 
//������
/**��ȡָ����ַ����ҳ����
 * ����������
 * Chen.Zhidong
 * 2011-02-18*/
public InputStream streampost(String remote_addr){
    URL infoUrl = null;
    InputStream inStream = null;
    try {
        infoUrl = new URL(remote_addr);
        URLConnection connection = infoUrl.openConnection();
        HttpURLConnection httpConnection = (HttpURLConnection)connection;
        int responseCode = httpConnection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            inStream = httpConnection.getInputStream();
        }
    } catch (MalformedURLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return inStream;
}