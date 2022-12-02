package io.liu.catnip.mvc.service.impl;

import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.entity.dto.ArticleDTO;
import io.liu.catnip.exception.wiki.ArticleNotFound;
import io.liu.catnip.mvc.mapper.WikiArticleMapper;
import io.liu.catnip.mvc.service.WikiService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // 对内容进行 Base64 解码
        String content = new String(Base64.decodeBase64(articleDTO.content()));

        // 写入数据库
        wikiArticleMapper.createArticle(userID, articleDTO.title(), content);
    }

    @Override
    public List<ArticleDO> listAllArticle() {
        // 从数据库查询数据
        List<ArticleDO> articleDOList = wikiArticleMapper.getAllArticle();

        return articleDOList;
    }

    @Override
    public ArticleDO queryArticle(String articleID) {
        // 从数据库查询数据
        ArticleDO articleDO = wikiArticleMapper.getArticle(articleID);

        // 文章不存在就抛出异常
        if (null == articleDO) {
            throw new ArticleNotFound();
        }

        return articleDO;
    }

    @Override
    public List<ArticleDO> listArticleByUserID(String userID) {
        // 从数据库查询数据
        List<ArticleDO> articleDOList = wikiArticleMapper.getArticleByUserID(userID);

        return articleDOList;
    }

//    private List<ArticleBO> articleDOList2ArticleBOList(List<ArticleDO> articleDOList) {

//
//        // 数据过滤
//        articleDOList.stream()
//                // 过滤掉 待审核文章
//                .filter(articleDO -> (ArticleStatus.EXAMINE & articleDO.status()) != ArticleStatus.EXAMINE)
////                // 过滤 隐藏的文章
//                .filter(articleDO -> (articleDO.status() & ArticleStatus.HIDDEN) != ArticleStatus.HIDDEN)
////                // 过滤 删除的文章
//                .filter(articleDO -> (articleDO.status() & ArticleStatus.DELETED) != ArticleStatus.DELETED)
//                .forEach(articleDO -> {
//                    articleBOList.add(new ArticleBO(articleDO.id(),
//                            articleDO.userID(),
//                            articleDO.username(),
//                            articleDO.title(),
//                            articleDO.content(),
//                            articleDO.createTime(),
//                            articleDO.modifyTime()));
//                });
//
//        return articleBOList;
//    }
}
