package chebotarskyi.dm.http.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
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
 * Immutable implementation of {@link TextProcessingProbability}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code ImmutableTextProcessingProbability.builder()}.
 * Use the static factory method to create immutable instances:
 * {@code ImmutableTextProcessingProbability.of()}.
 */
@SuppressWarnings({"all"})
@ParametersAreNonnullByDefault
@Generated({"Immutables.generator", "TextProcessingProbability"})
@Immutable
@CheckReturnValue
public final class ImmutableTextProcessingProbability
    implements TextProcessingProbability {
  private final double neg;
  private final double neutral;
  private final double pos;

  private ImmutableTextProcessingProbability(double neg, double neutral, double pos) {
    this.neg = neg;
    this.neutral = neutral;
    this.pos = pos;
  }

  /**
   * @return The value of the {@code neg} attribute
   */
  @JsonProperty("neg")
  @Override
  public double neg() {
    return neg;
  }

  /**
   * @return The value of the {@code neutral} attribute
   */
  @JsonProperty("neutral")
  @Override
  public double neutral() {
    return neutral;
  }

  /**
   * @return The value of the {@code pos} attribute
   */
  @JsonProperty("pos")
  @Override
  public double pos() {
    return pos;
  }

  /**
   * Copy the current immutable object by setting a value for the {@link TextProcessingProbability#neg() neg} attribute.
   * A value strict bits equality used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for neg
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableTextProcessingProbability withNeg(double value) {
    if (Double.doubleToLongBits(this.neg) == Double.doubleToLongBits(value)) return this;
    return new ImmutableTextProcessingProbability(value, this.neutral, this.pos);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link TextProcessingProbability#neutral() neutral} attribute.
   * A value strict bits equality used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for neutral
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableTextProcessingProbability withNeutral(double value) {
    if (Double.doubleToLongBits(this.neutral) == Double.doubleToLongBits(value)) return this;
    return new ImmutableTextProcessingProbability(this.neg, value, this.pos);
  }

  /**
   * Copy the current immutable object by setting a value for the {@link TextProcessingProbability#pos() pos} attribute.
   * A value strict bits equality used to prevent copying of the same value by returning {@code this}.
   * @param value A new value for pos
   * @return A modified copy of the {@code this} object
   */
  public final ImmutableTextProcessingProbability withPos(double value) {
    if (Double.doubleToLongBits(this.pos) == Double.doubleToLongBits(value)) return this;
    return new ImmutableTextProcessingProbability(this.neg, this.neutral, value);
  }

  /**
   * This instance is equal to all instances of {@code ImmutableTextProcessingProbability} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(@Nullable Object another) {
    if (this == another) return true;
    return another instanceof ImmutableTextProcessingProbability
        && equalTo((ImmutableTextProcessingProbability) another);
  }

  private boolean equalTo(ImmutableTextProcessingProbability another) {
    return Double.doubleToLongBits(neg) == Double.doubleToLongBits(another.neg)
        && Double.doubleToLongBits(neutral) == Double.doubleToLongBits(another.neutral)
        && Double.doubleToLongBits(pos) == Double.doubleToLongBits(another.pos);
  }

  /**
   * Computes a hash code from attributes: {@code neg}, {@code neutral}, {@code pos}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Doubles.hashCode(neg);
    h += (h << 5) + Doubles.hashCode(neutral);
    h += (h << 5) + Doubles.hashCode(pos);
    return h;
  }

  /**
   * Prints the immutable value {@code TextProcessingProbability} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper("TextProcessingProbability")
        .omitNullValues()
        .add("neg", neg)
        .add("neutral", neutral)
        .add("pos", pos)
        .toString();
  }

  /**
   * Utility type used to correctly read immutable object from JSON representation.
   * @deprecated Do not use this type directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonDeserialize
  @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE)
  static final class Json implements TextProcessingProbability {
    double neg;
    boolean negIsSet;
    double neutral;
    boolean neutralIsSet;
    double pos;
    boolean posIsSet;
    @JsonProperty("neg")
    public void setNeg(double neg) {
      this.neg = neg;
      this.negIsSet = true;
    }
    @JsonProperty("neutral")
    public void setNeutral(double neutral) {
      this.neutral = neutral;
      this.neutralIsSet = true;
    }
    @JsonProperty("pos")
    public void setPos(double pos) {
      this.pos = pos;
      this.posIsSet = true;
    }
    @Override
    public double neg() { throw new UnsupportedOperationException(); }
    @Override
    public double neutral() { throw new UnsupportedOperationException(); }
    @Override
    public double pos() { throw new UnsupportedOperationException(); }
  }

  /**
   * @param json A JSON-bindable data structure
   * @return An immutable value type
   * @deprecated Do not use this method directly, it exists only for the <em>Jackson</em>-binding infrastructure
   */
  @Deprecated
  @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
  static ImmutableTextProcessingProbability fromJson(Json json) {
    ImmutableTextProcessingProbability.Builder builder = ImmutableTextProcessingProbability.builder();
    if (json.negIsSet) {
      builder.neg(json.neg);
    }
    if (json.neutralIsSet) {
      builder.neutral(json.neutral);
    }
    if (json.posIsSet) {
      builder.pos(json.pos);
    }
    return builder.build();
  }

  /**
   * Construct a new immutable {@code TextProcessingProbability} instance.
   * @param neg The value for the {@code neg} attribute
   * @param neutral The value for the {@code neutral} attribute
   * @param pos The value for the {@code pos} attribute
   * @return An immutable TextProcessingProbability instance
   */
  public static ImmutableTextProcessingProbability of(double neg, double neutral, double pos) {
    return new ImmutableTextProcessingProbability(neg, neutral, pos);
  }

  /**
   * Creates an immutable copy of a {@link TextProcessingProbability} value.
   * Uses accessors to get values to initialize the new immutable instance.
   * If an instance is already immutable, it is returned as is.
   * @param instance The instance to copy
   * @return A copied immutable TextProcessingProbability instance
   */
  public static ImmutableTextProcessingProbability copyOf(TextProcessingProbability instance) {
    if (instance instanceof ImmutableTextProcessingProbability) {
      return (ImmutableTextProcessingProbability) instance;
    }
    return ImmutableTextProcessingProbability.builder()
        .from(instance)
        .build();
  }

  /**
   * Creates a builder for {@link ImmutableTextProcessingProbability ImmutableTextProcessingProbability}.
   * @return A new ImmutableTextProcessingProbability builder
   */
  public static ImmutableTextProcessingProbability.Builder builder() {
    return new ImmutableTextProcessingProbability.Builder();
  }

  /**
   * Builds instances of type {@link ImmutableTextProcessingProbability ImmutableTextProcessingProbability}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @NotThreadSafe
  public static final class Builder {
    private static final long INIT_BIT_NEG = 0x1L;
    private static final long INIT_BIT_NEUTRAL = 0x2L;
    private static final long INIT_BIT_POS = 0x4L;
    private long initBits = 0x7L;

    private double neg;
    private double neutral;
    private double pos;

    private Builder() {
    }

    /**
     * Fill a builder with attribute values from the provided {@code TextProcessingProbability} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    public final Builder from(TextProcessingProbability instance) {
      Objects.requireNonNull(instance, "instance");
      neg(instance.neg());
      neutral(instance.neutral());
      pos(instance.pos());
      return this;
    }

    /**
     * Initializes the value for the {@link TextProcessingProbability#neg() neg} attribute.
     * @param neg The value for neg 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("neg")
    public final Builder neg(double neg) {
      this.neg = neg;
      initBits &= ~INIT_BIT_NEG;
      return this;
    }

    /**
     * Initializes the value for the {@link TextProcessingProbability#neutral() neutral} attribute.
     * @param neutral The value for neutral 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("neutral")
    public final Builder neutral(double neutral) {
      this.neutral = neutral;
      initBits &= ~INIT_BIT_NEUTRAL;
      return this;
    }

    /**
     * Initializes the value for the {@link TextProcessingProbability#pos() pos} attribute.
     * @param pos The value for pos 
     * @return {@code this} builder for use in a chained invocation
     */
    @CanIgnoreReturnValue 
    @JsonProperty("pos")
    public final Builder pos(double pos) {
      this.pos = pos;
      initBits &= ~INIT_BIT_POS;
      return this;
    }

    /**
     * Builds a new {@link ImmutableTextProcessingProbability ImmutableTextProcessingProbability}.
     * @return An immutable instance of TextProcessingProbability
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImmutableTextProcessingProbability build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableTextProcessingProbability(neg, neutral, pos);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = Lists.newArrayList();
      if ((initBits & INIT_BIT_NEG) != 0) attributes.add("neg");
      if ((initBits & INIT_BIT_NEUTRAL) != 0) attributes.add("neutral");
      if ((initBits & INIT_BIT_POS) != 0) attributes.add("pos");
      return "Cannot build TextProcessingProbability, some of required attributes are not set " + attributes;
    }
  }
}
