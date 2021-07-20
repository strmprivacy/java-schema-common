package io.streammachine.schemas;

/**
 * An event to be sent to Stream Machine. All events that are sent by the Stream Machine
 * Java Driver must implement this interface.
 */
public interface StreamMachineEvent {

    /**
     * Get the Schema Ref for the Schema that goes with this event.
     * @return The Schema Ref (formatted as: 'handle/name/version').
     */
    String getSchemaRef();

    /**
     * Old version of {@link StreamMachineEvent#getSchema()}.
     * @return The schema.
     * @deprecated This method will be removed in java-schema-common 1.0.0.
     */
    @Deprecated // Commented, because this requires JDK9+ (forRemoval = true)
    default Object getStrmSchema() {
        return getSchema();
    }

    /**
     * Get the Schema instance used to serialize the event.
     * @return The schema.
     */
    Object getSchema();
}
