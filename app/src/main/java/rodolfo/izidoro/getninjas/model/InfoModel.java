package rodolfo.izidoro.getninjas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class InfoModel {

    @SerializedName("label")
    @Expose
    private String label;

    @SerializedName("value")
    @Expose
    private Object value;


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    //    public List<String> getValue() {
//        return value;
//    }
//
//    public void setValue(List<String> value) {
//        this.value = value;
//    }
//
//
//    public static class OptionsDeserilizer implements JsonDeserializer<InfoModel> {
//
//        @Override
//        public InfoModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            InfoModel options = new Gson().fromJson(json, InfoModel.class);
//            JsonObject jsonObject = json.getAsJsonObject();
//
//            if (jsonObject.has("value")) {
//                JsonElement elem = jsonObject.get("value");
//                if (elem != null && !elem.isJsonNull()) {
//                    String valuesString = elem.getAsString();
//                    if (!TextUtils.isEmpty(valuesString)){
//                        List<String> values = new Gson().fromJson(valuesString, new TypeToken<ArrayList<String>>() {}.getType());
//                        options.setValue(values);
//                    }
//                }
//            }
//            return options ;
//        }
//    }
}
