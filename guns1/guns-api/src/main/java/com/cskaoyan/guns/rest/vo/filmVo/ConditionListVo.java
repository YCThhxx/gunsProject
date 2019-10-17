package com.cskaoyan.guns.rest.vo.filmVo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ConditionListVo implements Serializable {

    private static final long serialVersionUID = 4381464925501109135L;
    List<CatInfoVo> catInfo;
    List<SourceInfoVo> sourceInfo;
    List<YearInfoVo> yearInfo;

    public ConditionListVo(List<CatInfoVo> catInfo, List<SourceInfoVo> sourceInfo, List<YearInfoVo> yearInfo) {
        this.catInfo = catInfo;
        this.sourceInfo = sourceInfo;
        this.yearInfo = yearInfo;
    }
}
