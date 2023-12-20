package com.project.cleansnowtown.domain.notice;

import com.project.cleansnowtown.domain.BaseEntity;
import com.project.cleansnowtown.domain.Search;
import com.project.cleansnowtown.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "notice")
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    private String title;
    @Column(name = "notice_contents")
    private String contents;
    @Column(name = "notice_count")
    private int count;
    private int likes;
    private LocalDateTime writeDate;

    @Embedded
    private Search search;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Notice(String title, String contents, int count, int likes, LocalDateTime writeDate, Search search, Member member){
        this.title = title;
        this.contents = contents;
        this.count = count;
        this.likes = likes;
        this.writeDate = writeDate;
        this.search = search;
        this.member = member;
    }
}
