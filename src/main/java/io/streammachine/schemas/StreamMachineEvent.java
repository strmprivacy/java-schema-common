package io.streammachine.schemas;

import java.io.IOException;

public interface StreamMachineEvent {
    String getStrmSchemaId();

    Object getStrmSchema() throws IOException;
}
