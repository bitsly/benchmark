package org.bitsly.benchmark.springboot.web.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 没有出框架之前对值进行修改
 */
@Slf4j
@Controller
@ControllerAdvice(annotations = {RestController.class, RestController.class})
public class ResponseResultHandler  extends BasicErrorController implements ResponseBodyAdvice<Object>{

    @Data
    private static class ResultWrap{
        private Boolean success;
        private Integer code;
        private String msg;
        private Object data;
    }

    /**
     * 实现ResponseBodyAdvice且声明@RestControllerAdvice，对正确的返回值进行包装成指定json格式
     */

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //返回true表示本ResponseBodyAdvice的实现工作
        //此处配置对String类型的转换处理，去掉的话会出现can not cast的问题
        return !aClass.equals(StringHttpMessageConverter.class);
    }

    @Override
    @ResponseBody
    public ResultWrap beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
            Class aClass, ServerHttpRequest serverHttpRequest,
            ServerHttpResponse serverHttpResponse) {
        //可以对返回值进行修改
        ResultWrap result = new ResultWrap();
        result.setSuccess(true);
        result.setCode(0);
        result.setMsg("ok");
        result.setData(o);
        return result;
    }

    /**
     * 声明@RestControllerAdvice，且对框架内的异常进行处理
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String handleError(Exception e) {
        //可以对框架内抛出的异常做包装
        return "err";
    }


    /**
     * 在DispatcherServlet捕获到异常后，调用本类将异常包装成通用的结构体
     * 继承BasicErrorController且声明@Controller，对框架异常进行封装，统一返回异常
     */

    public ResponseResultHandler(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的HTML响应，修改为web请求的框架异常也返回son
     */
    @Override
    @RequestMapping(produces = {"text/html"}, value = "/error")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * 覆盖默认的Json响应
     */
    @Override
    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));

        Integer status = (Integer) body.get("status");
        String path = (String) body.get("path");
        String error = (String) body.get("error");
        String exception = (String) body.get("exception");
        String message = (String) body.get("message");

        if (Objects.isNull(status) ||
                status < HttpStatus.BAD_REQUEST.value() ||
                status >= HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            log.error("status: {} , error: {} , exception: {} , message: {} , path: {}",
                    status, error, exception, message, path);
        }

        ResultWrap result = new ResultWrap();
        result.setSuccess(false);
        result.setCode(-1);
        result.setMsg(message);
        result.setData(null);

        Map<String, Object> resultMap = new HashMap<>();
        BeanMap beanMap = BeanMap.create(result);
        for (Object key : beanMap.keySet()) {
            resultMap.put((String)key, beanMap.get(key));
        }
        return new ResponseEntity<>(resultMap, getStatus(request));
    }
}
