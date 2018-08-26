
package com.frapaego.movie;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

public final class DTOUtils {

	private static final ModelMapper INSTANCE = new ModelMapper();

	private DTOUtils() {
		throw new InstantiationError("No puede instanciar esta clase");
	}

	public static <S, T> T map(final S source, final Class<T> targetClass) {
		return INSTANCE.map(source, targetClass);
	}

	public static <S, T> void mapTo(final S source, final T dist) {
		INSTANCE.map(source, dist);
	}

	public static <S, T> List<T> mapList(final List<S> source, final Class<T> targetClass) {
		final List<T> list = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			final T target = INSTANCE.map(source.get(i), targetClass);
			list.add(target);
		}

		return list;
	}

	public static <S, T> Page<T> mapPage(final Page<S> source, final Class<T> targetClass) {
		final List<S> sourceList = source.getContent();

		final List<T> list = new ArrayList<>();
		for (int i = 0; i < sourceList.size(); i++) {
			final T target = INSTANCE.map(sourceList.get(i), targetClass);
			list.add(target);
		}

		return new PageImpl<>(list, new PageRequest(source.getNumber(), source.getSize(), source.getSort()),
				source.getTotalElements());
	}
}
