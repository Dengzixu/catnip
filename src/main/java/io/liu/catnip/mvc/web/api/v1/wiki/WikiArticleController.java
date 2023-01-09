package io.liu.catnip.mvc.web.api.v1.wiki;

import io.liu.catnip.Utils.JWTUtils;
import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.entity.DO.CategoryDO;
import io.liu.catnip.entity.dto.ArticleDTO;
import io.liu.catnip.entity.vo.ArticleVO;
import io.liu.catnip.exception.auth.TokenExpiredException;
import io.liu.catnip.model.APIResponseMap;
import io.liu.catnip.mvc.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wiki/article")
public class WikiArticleController {
    // logger
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WikiArticleController.class);

    private final JWTUtils jwtUtils = new JWTUtils();

    private final WikiService wikiService;

    @Autowired
    public WikiArticleController(WikiService wikiService) {
        this.wikiService = wikiService;
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponseMap> create(@RequestHeader("Authorization") String authorization,
                                                 @Validated @RequestBody ArticleDTO articleDTO,
                                                 BindingResult bindingResult) {
        // 数据校验
        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(APIResponseMap.FAILED(-1, bindingResult.getFieldError()));
        }

        // Decode 用户ID
        long userID = jwtUtils.decode(authorization).orElseThrow(TokenExpiredException::new);
        logger.info("[wiki][article][create] Decode userid: {}", userID);

        // 文章发布逻辑
        wikiService.createArticle(articleDTO, userID);


        return ResponseEntity.ok(APIResponseMap.SUCCEEDED(""));
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponseMap> listAll(@RequestHeader(name = "Authorization", required = false) String authorization) {
        List<ArticleDO> articleDOList = wikiService.listAllArticle();


        // ArticleDO to ArticleVO
        List<ArticleVO> articleVOList = new LinkedList<>();
        articleDOList.forEach(articleDO -> {
            articleVOList.add(new ArticleVO(articleDO.id(),
                    articleDO.userID(),
                    articleDO.username(),
                    articleDO.title(),
                    articleDO.content(),
                    articleDO.categoryID(),
                    articleDO.categoryName(),
                    articleDO.status(),
                    articleDO.createTime(),
                    articleDO.modifyTime()));
        });

        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", articleVOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseMap> view(@RequestHeader(name = "Authorization", required = false) String authorization,
                                               @PathVariable String id) {

        // 根据 ID 获取文章
        ArticleDO articleDO = wikiService.queryArticle(id);

        // ArticleDO to ArticleVO
        ArticleVO articleVO = new ArticleVO(articleDO.id(),
                articleDO.userID(),
                articleDO.username(),
                articleDO.title(),
                articleDO.content(),
                articleDO.categoryID(),
                articleDO.categoryName(),
                articleDO.status(),
                articleDO.createTime(),
                articleDO.modifyTime());


        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", articleVO));
    }

    @GetMapping("/uid-{userID}")
    public ResponseEntity<APIResponseMap> listByUserID(@RequestHeader(name = "Authorization", required = false) String authorization,
                                                       @PathVariable String userID) {
        // 根据 UserID 获取文章
        List<ArticleDO> articleDOList = wikiService.listArticleByUserID(userID);

        // ArticleDO to ArticleVO
        List<ArticleVO> articleVOList = new LinkedList<>();
        articleDOList.forEach(articleDO -> {
            articleVOList.add(new ArticleVO(articleDO.id(),
                    articleDO.userID(),
                    articleDO.username(),
                    articleDO.title(),
                    articleDO.content(),
                    articleDO.categoryID(),
                    articleDO.categoryName(),
                    articleDO.status(),
                    articleDO.createTime(),
                    articleDO.modifyTime()));
        });


        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", articleVOList));
    }

    @GetMapping("/category/all")
    public ResponseEntity<APIResponseMap> listAllCategory(@RequestHeader(name = "Authorization", required = false) String authorization) {
        List<CategoryDO> categoryDOList = wikiService.listCategory();

        // CategoryDO to CategoryVO
        List<CategoryDO> categoryVOList = new LinkedList<>();
        categoryDOList.forEach(categoryDO -> {
            categoryVOList.add(new CategoryDO(categoryDO.id(),
                    categoryDO.name()));
        });


        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", categoryVOList));
    }
}
