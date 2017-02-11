package converters;

public interface Converter<E,S> {
	public S convert(int width, int height);
	public S convertExtra(int width, int height);
}
