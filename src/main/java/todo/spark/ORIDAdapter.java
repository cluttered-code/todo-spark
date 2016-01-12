package todo.spark;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;

import java.io.IOException;

/**
 * @author david.clutter@greyrocksoft.com
 */
public class ORIDAdapter extends TypeAdapter<ORID> {

    @Override
    public void write(final JsonWriter jsonWriter, final ORID orid) throws IOException {
        jsonWriter.value(orid.getClusterId() + "-" + orid.getClusterPosition());
    }

    @Override
    public ORID read(final JsonReader jsonReader) throws IOException {
        final String oridString = jsonReader.nextString();
        final String[] oridSplit = oridString.split("-");
        final int clusterId = Integer.parseInt(oridSplit[0]);
        final long position = Long.parseLong(oridSplit[1]);
        return new ORecordId(clusterId, position);
    }
}