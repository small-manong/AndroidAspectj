//第一种
/**获取参数(ArrayList<NameValuePair> nameValuePairs,String url)后post给远程服务器
 * 将获得的返回结果(String)返回给调用者
 * 本函数适用于查询数量较少的时候
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
 
//第二种
/**获取参数指定的网页代码，将其返回给调用者，由调用者对其解析
 * 返回String
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
 
//第三种
/**获取指定地址的网页数据
 * 返回数据流
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