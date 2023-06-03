package tools.redstone.marshal;

public interface Displayable {
    /**
     * Returns the generic display name of the object, e.g. "an integer".
     * <br>
     * Used in the following way: "Expected {an integer}"
     *
     * @return The generic display name of the object
     */
    String getGenericDisplayName();

    /**
     * Returns the singular display name of the object, e.g. "integer".
     * <br>
     * Used in the following way: "Expected 1 {integer}"
     *
     * @return The singular display name of the object
     */
    String getSingularDisplayName();

    /**
     * Returns the plural display name of the object, e.g. "integers".
     * <br>
     * Used in the following way: "Expected 5 {integers}"
     *
     * @return The plural display name of the object
     */
    String getPluralDisplayName();
}
