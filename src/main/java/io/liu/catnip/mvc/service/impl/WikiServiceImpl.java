package io.liu.catnip.mvc.service.impl;

import io.liu.catnip.entity.dto.ArticleDTO;
import io.liu.catnip.mvc.mapper.WikiArticleMapper;
import io.liu.catnip.mvc.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WikiServiceImpl implements WikiService {
    // logger
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WikiServiceImpl.class);

    private final WikiArticleMapper wikiArticleMapper;

    @Autowired
    public WikiServiceImpl(WikiArticleMapper wikiArticleMapper) {
        this.wikiArticleMapper = wikiArticleMapper;
    }

    @Override
    public void createArticle(ArticleDTO articleDTO, Long userID) {
        // 写入数据库
        wikiArticleMapper.createArticle(userID, articleDTO.title(), articleDTO.content());
    }
}
