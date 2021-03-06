package io.strmprivacy.schemas;

/**
 * An event to be sent to STRM Privacy. All events that are sent by the Strm Privacy
 * Java Driver must implement this interface.
 */
public interface StrmPrivacyEvent {

    /**
     * Get the Schema Ref for the Schema that goes with this event.
     * @return The Schema Ref (formatted as: 'handle/name/version').
     */
    String getSchemaRef();

    /**
     * Old version of {@link StrmPrivacyEvent#getSchema()}.
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
