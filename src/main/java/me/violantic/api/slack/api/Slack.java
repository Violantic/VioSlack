package me.violantic.api.slack.api;

import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedOutputStream;
import java.net.URL;

/**
 * @author Ethan
 */
public class Slack {

    public String user;
    public String webhook_url;
    public String avatar_url = "https://minotar.net/helm/{user}/150.png";

    public Slack(String user, String key, String displayUser) {
        this.user = user;
        this.webhook_url = "https://hooks.slack.com/services/" + key;
        avatar_url.replace("{user}", displayUser);
    }

    public String getUser() {
        return this.user;
    }

    public void send(String channel, String message) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("channel", channel);
            jsonObject.put("text", message);
            jsonObject.put("username", getUser());
            jsonObject.put("icon_url", avatar_url);

                    try {
                        HttpsURLConnection huc = (HttpsURLConnection) (new URL(webhook_url)).openConnection();
                        huc.setRequestMethod("POST");
                        huc.setDoOutput(true);

                        try {
                            BufferedOutputStream bufOut = new BufferedOutputStream(huc.getOutputStream());

                            try {
                                String jsonStr = "payload=" + jsonObject.toString();
                                bufOut.write(jsonStr.getBytes("utf8"));
                                bufOut.flush();
                                bufOut.close();
                            } finally {
                                if (bufOut != null) {
                                    bufOut.close();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        huc.getResponseCode();
                        huc.disconnect();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void sendDirect(String user, String message) {
        send("@" + user, message);
    }

}
