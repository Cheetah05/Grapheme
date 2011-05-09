package uk.me.graphe.shared.messages.factories;

import uk.me.graphe.shared.jsonwrapper.JSONException;
import uk.me.graphe.shared.jsonwrapper.JSONObject;
import uk.me.graphe.shared.messages.LogoutMessage;
import uk.me.graphe.shared.messages.Message;

public class LogoutFactory implements ConversionFactory{
    @Override
    public Message make(JSONObject o) {

        return new LogoutMessage();

    }
}
