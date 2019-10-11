package com.gdaas.iard.datafill.admin.util;

import com.alibaba.fastjson.JSON;
import com.gdaas.iard.datafill.common.SystemConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe:JWT
 *  使用场景:一种情况是webapi,另一种情况是多web服务器下实现无状态分布式身份验证。 JWT是JSON Web Token的缩写，即JSON Web令牌。JSON Web令牌（JWT）是一种紧凑的、URL安全的方式， 用来表示要在双方之间传递的“声明”。JWT中的声明被编码为JSON对象，用作JSON Web签名（JWS）结构的有效内容或JSON Web加密（JWE）结构的明文，使得声明能够被：数字签名、 或利用消息认证码（MAC）保护完整性、加密。
 * @author:houkai
 * @Date: 2018/3/26 16:49
 */
public class JwtUtil {

    /**
     * 解密
     * @return
     */
    public static String parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SystemConstant.KEYT.getBytes())
                    .parseClaimsJws(jsonWebToken).getBody();
            String o = (String) claims.get("user");
            return o;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 前三个参数为自己用户token的一些信息比如id，权限，名称等。不要将隐私信息放入（大家都可以获取到）
     * @return
     */
    public static String createJWT(String s) {
    	Map<String,String> map = new HashMap<>();
    	map.put("user", s);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setPayload(JSON.toJSON(map).toString())
                .signWith(signatureAlgorithm, SystemConstant.KEYT.getBytes()); //估计是第三段密钥
        //生成JWT
        return builder.compact();
    }

}