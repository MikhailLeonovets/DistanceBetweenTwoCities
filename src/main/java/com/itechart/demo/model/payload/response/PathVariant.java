package com.itechart.demo.model.payload.response;

import com.itechart.demo.model.entity.Path;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PathVariant {
	private Set<Path> paths;
}
