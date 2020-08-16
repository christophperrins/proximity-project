package uk.co.bpdts.proximity.mapping;

import org.springframework.core.convert.converter.Converter;

import uk.co.bpdts.proximity.models.LengthUnit;

/**
 * Converts strings to lengthUnit
 * @author chris
 *
 */
public class StringToLengthUnitConverter implements Converter<String, LengthUnit> {

	/**
	 * Ignores case of string when converting to LengthUnit Enum
	 */
	@Override
    public LengthUnit convert(String source) {
        return LengthUnit.valueOf(source.toUpperCase());
    }
}
