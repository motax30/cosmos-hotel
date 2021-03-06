/* eslint-env node */
'use strict';

module.exports = function(environment) {
  let ENV = {
    modulePrefix: 'frontend',
    environment,
    rootURL: '/',
    locationType: 'router-scroll',
    historySupportMiddleware: true,
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false
      }
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    }
  };

  if (environment === 'development') {
//     ENV.APP.LOG_RESOLVER = true;
//     ENV.APP.LOG_ACTIVE_GENERATION = true;
//     ENV.APP.LOG_TRANSITIONS = true;
//     ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
//     ENV.APP.LOG_VIEW_LOOKUPS = true;
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {

  }

  ENV['i18n'] = {
    defaultLocale: 'pt-br'
  };

  ENV['ember-form-for'] = {
    buttonClasses: ['btn'],
    fieldClasses: ['form-group'],
    fieldHasErrorClasses: ['has-danger'],
    errorClasses: ['form-control-feedback'],
    hintClasses: ['form-field--hint'],
    inputClasses: ['form-control'],
    labelClasses: [''],
    resetClasses: ['btn btn-secondary'],
    submitClasses: ['btn btn-primary'],
    errorsPath: 'error.PROPERTY_NAME.validation'
  };

  ENV['simple-auth'] = {
	  authorizer: 'authorizer:token',
	  crossOriginWhitelist: ['http://localhost:3000']
  };

  ENV['ember-simple-auth-token'] = {
	  serverTokenEndpoint: 'http://localhost:3000/api/auth/',
	  refreshAccessTokens: true,
	  refreshLeeway: 300, // Refresh the token 5 minutes (300s) before it expires.
	  serverTokenRefreshEndpoint: 'http://localhost:3000/auth/token-refresh/'
  };

  return ENV;
};
