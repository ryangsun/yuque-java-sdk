package com.bumao.model.yuquesdk.vo;

import com.bumao.model.yuquesdk.domain.BookAbilities;
import com.bumao.model.yuquesdk.domain.BookDetailSerializer;
import lombok.Data;

@Data
public class BookDetailVo {
    private BookAbilities abilities;
    private BookDetailSerializer data;
}
