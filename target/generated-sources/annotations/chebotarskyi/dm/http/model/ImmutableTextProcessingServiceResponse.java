package chebotarskyi.dm.http.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.List;
import java.util.Objects;
import javax.annotation.CheckReturnValue;
import javax.annotation.Generated;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

/**
 * Immutable implementation of {@link TextProcessingServiceResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableTextProcessingServiceResponse.builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableTextProcessingServiceResponse.of()}.
 */
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@Generated({"Immutables.generator", "TextProcessingServiceResponse"})
@Immutable
@CheckReturnValue
public final class ImmutableTextProcessingServiceResponse
    implements TextProcessingServiceResponse {
  private final String label;
  private final TextProcessingProbability probability;

  private ImmutableTextProcessingServiceResponse(String label, TextProcessingProbability probability) {
    this.label = Objects.requireNonNull(label, "label");
    this.probability = Objects.requireNonNull(probability, "probability");
  }

  private ImmutableTextProcessingServiceResponse(
      ImmutableTextProcessingServiceResponse original,
      String label,
      TextProcessingProbability probability) {
    this.label = label;
    this.probability = probability;
  }

  /**
   * @return The value of the {@code label} attribute
   */
  @JsonProperty("label")
  @Override
  public String label() {
    return label;
  }

  /**
   * @return The value of the {@code probability} attribute
   */
  @JsonProperty("probability")
  @Override
  public TextProcessingProbability probability() {
    return probability;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link TextProcessingServiceResponse#label() label} attribute.
   * An equals check used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for label
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableTextProcessingServiceResponse withLabel(String value) {
    if (this.label.equals(value)) return this;
    String newValue = Objects.requireNonNull(value, "label");
    return new ImmutableTextProcessingServiceResponse(this, newValue, this.probability);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link TextProcessingServiceResponse#probability() probability} attribute.
   * A shallow reference equality check is used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for probability
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableTextProcessingServiceResponse withProbability(TextProcessingProbability value) {
    if (this.probability == value) return this;
    TextProcessingProbability newValue = Objects.requireNonNull(value, "probability");
    return new ImmutableTextProcessingServiceResponse(this, this.label, newValue);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableTextProcessingServiceResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableTextProcessingServiceResponse
        && equalTo((ImmutableTextProcessingServiceResponse) another);
  }

  private boolean equalTo(ImmutableTextProcessingServiceResponse another) {
    return label.equals(another.label)
        && probability.equals(another.probability);
  }

  /**
   * Computes a hash code from attributes: {@code label}, {@code probability}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + label.hashCode();
    h += (h << 5) + probability.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code TextProcessingServiceResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("TextProcessingServiceResponse")
        .omitNullValues()
        .add("label", label)
        .add("probability", probability)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements TextProcessingServiceResponse {
    @Nullable String label;
    @Nullable TextProcessingProbability probability;
    @JsonProperty("label")
    public void setLabel(String label) {
      this.label = label;
    }
    @JsonProperty("probability")
    public void setProbability(TextProcessingProbability probability) {
      this.probability = probability;
    }
    @Override
    public String label() { throw new UnsupportedOperationException(); }
    @Override
    public TextProcessingProbability probability() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableTextProcessingServiceResponse fromJson(Json json) {
    ImmutableTextProcessingServiceResponse.Builder builder = ImmutableTextProcessingServiceResponse.builder();
    if (json.label != null) {
      builder.label(json.label);
    }
    if (json.probability != null) {
      builder.probability(json.probability);
    }
    return builder.build();
  }

  /**
   * Construct a new immutable {@code TextProcessingServiceResponse} instance.
   * @param label The value for the {@code label} attribute
   * @param probability The value for the {@code probability} attribute
   * @return An immutable TextProcessingServiceResponse instance
   */
  public static ImmutableTextProcessingServiceResponse of(String label, TextProcessingProbability probability) {
    return new ImmutableTextProcessingServiceResponse(label, probability);
  }

  /**
   * Creates an immutable copy of a {@link TextProcessingServiceResponse} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable TextProcessingServiceResponse instance
   */
  public static ImmutableTextProcessingServiceResponse copyOf(TextProcessingServiceResponse instance) {
    if (instance instanceof ImmutableTextProcessingServiceResponse) {
      return (ImmutableTextProcessingServiceResponse) instance;
    }
    return ImmutableTextProcessingServiceResponse.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableTextProcessingServiceResponse ImmutableTextProcessingServiceResponse}.
   * @return A new ImmutableTextProcessingServiceResponse builder
   */
  public static ImmutableTextProcessingServiceResponse.Builder builder() {
    return new ImmutableTextProcessingServiceResponse.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableTextProcessingServiceResponse ImmutableTextProcessingServiceResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_LABEL = 0x1L;
    private static final long INIT_BIT_PROBABILITY = 0x2L;
    private long initBits = 0x3L;

    private @Nullable String label;
    private @Nullable TextProcessingProbability probability;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code TextProcessingServiceResponse} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(TextProcessingServiceResponse instance) {
      Objects.requireNonNull(instance, "instance");
      label(instance.label());
      probability(instance.probability());
      return this;
    }

    /**
     * Initializes the value for the {@link TextProcessingServiceResponse#label() label} attribute.
     * @param label The value for label 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("label")
    public final Builder label(String label) {
      this.label = Objects.requireNonNull(label, "label");
      initBits &= ~INIT_BIT_LABEL;
      return this;
    }

    /**
     * Initializes the value for the {@link TextProcessingServiceResponse#probability() probability} attribute.
     * @param probability The value for probability 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("probability")
    public final Builder probability(TextProcessingProbability probability) {
      this.probability = Objects.requireNonNull(probability, "probability");
      initBits &= ~INIT_BIT_PROBABILITY;
      return this;
    }

    /**
     * Builds a new {@link ImmutableTextProcessingServiceResponse ImmutableTextProcessingServiceResponse}.
     * @return An immutable instance of TextProcessingServiceResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableTextProcessingServiceResponse build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableTextProcessingServiceResponse(null, label, probability);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = Lists.newArrayList();
      if ((initBits & INIT_BIT_LABEL) != 0) attributes.add("label");
      if ((initBits & INIT_BIT_PROBABILITY) != 0) attributes.add("probability");
      return "Cannot build TextProcessingServiceResponse, some of required attributes are not set " + attributes;
    }
  }
}
