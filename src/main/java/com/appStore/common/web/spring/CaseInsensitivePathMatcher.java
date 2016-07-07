/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.appStore.common.web.spring;

import org.springframework.util.AntPathMatcher;

import java.util.Map;

/**
 *  覆盖缺省的{@link AntPathMatcher}，解决URL缺省敏感匹配的问题。
 *
 *  @author xiachuan at 2016/7/7 20:01。
 */

public class CaseInsensitivePathMatcher extends AntPathMatcher {

    public CaseInsensitivePathMatcher() {
        super();
    }

    /**
     * 请求URL不敏感匹配。
     *
     * @param pattern the pattern to match against
     * @param path the path String to test
     * @param fullMatch whether a full pattern match is required (else a pattern match as far as the given base path goes is sufficient)
     * @return {@code true} if the supplied {@code path} matched, {@code false} if it didn't
     */
    @Override
    protected boolean doMatch(String pattern, String path, boolean fullMatch, Map<String, String> uriTemplateVariables) {
        return super.doMatch(pattern.toLowerCase(), path.toLowerCase(), fullMatch, uriTemplateVariables);
    }
}

