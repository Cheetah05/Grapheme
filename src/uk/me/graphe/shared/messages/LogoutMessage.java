package uk.me.graphe.shared.messages;

import uk.me.graphe.shared.jsonwrapper.JSONException;
import uk.me.graphe.shared.jsonwrapper.JSONImplHolder;
import uk.me.graphe.shared.jsonwrapper.JSONObject;

public class LogoutMessage extends Message {

    public LogoutMessage(){
        
    }
    
    @Override
    public String getMessage() {
        return "logout";
    }

    @Override
    public String toJson() {
        JSONObject repr = JSONImplHolder.make();
        try {
            repr.put("message", this.getMessage());
        } catch (JSONException jse) {
            throw new Error(jse);
        }
        return repr.toString();
    }
    
}
