function fn() {
    let env = karate.env; // get system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev';
    }
    const config = {
        env: env,
        AppUrl: '/api/flights/reservation' //The URL of the API
    };
    if (env === 'dev') {
        config.AppUrl = 'http://localhost:8090' + config.AppUrl //The entire URL with the host
    } else if (env === 'e2e') {
        config.AppUrl = 'http://localhost:8090' + config.AppUrl
    }
    return config;
}