package com.domain.embedded;

import com.dslplatform.json.CompiledJson;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@CompiledJson
public class GolfCourseObject {

    private Integer id;

    private String name;
}
