package br.com.aplicacaoaps.apirest.models.token;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24*60*60*100;
    public static final String SIGNING_KEY = "U;~T*!!cpKjBu)ikm-.G)vi-Os-7QjvOp}\"A+qgZ|.60!d<W\\ pXMdWGHxka)DmvteDEts`DbVE~u+eP";
    public static final String TOKEN_PREFIX = "bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
