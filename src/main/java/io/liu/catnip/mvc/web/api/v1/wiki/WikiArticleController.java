package io.liu.catnip.mvc.web.api.v1.wiki;

import io.liu.catnip.Utils.JWTUtils;
import io.liu.catnip.entity.DO.ArticleDO;
import io.liu.catnip.entity.dto.ArticleDTO;
import io.liu.catnip.exception.auth.TokenExpiredException;
import io.liu.catnip.model.APIResponseMap;
import io.liu.catnip.mvc.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", articleDOList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponseMap> view(@RequestHeader(name = "Authorization", required = false) String authorization,
                                               @PathVariable String id) {

        // 根据 ID 获取文章
        ArticleDO articleDO = wikiService.queryArticle(id);

        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", articleDO));
    }

    @GetMapping("/uid-{userID}")
    public ResponseEntity<APIResponseMap> listByUserID(@RequestHeader(name = "Authorization", required = false) String authorization,
                                                       @PathVariable String userID) {
        // 根据 UserID 获取文章
        List<ArticleDO> articleDOList = wikiService.listArticleByUserID(userID);


        return ResponseEntity.ok(APIResponseMap.SUCCEEDED("", articleDOList));
    }
}
