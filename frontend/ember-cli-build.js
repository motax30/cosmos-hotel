/* eslint-env node */
'use strict';

const EmberApp = require('ember-cli/lib/broccoli/ember-app');

module.exports = function(defaults) {
	let app = new EmberApp(defaults, {
		// Add options here	
		sassOptions: {
			includePaths: [
				'bower_components/simple-line-icons/scss'
				],
			sourceMap: false
		}
  });

  // Use `app.import` to add additional libraries to the generated
  // output files.
  //
  // If you need to use different assets in different
  // environments, specify an object as the first parameter. That
  // object's keys should be the environment name and the values
  // should be the asset to use in that environment.
  //
  // If the library that you are including contains AMD or ES6
  // modules that you would like to import into your application
  // please specify an object with the list of modules as keys
  // along with the exports of each module as its value.
	app.import('bower_components/simple-line-icons/fonts/Simple-Line-Icons.eot',{
		destDir: 'fonts'
	});
	app.import('bower_components/simple-line-icons/fonts/Simple-Line-Icons.ttf',{
		destDir: 'fonts'
	});
	app.import('bower_components/simple-line-icons/fonts/Simple-Line-Icons.woff',{
		destDir: 'fonts'
	});
	app.import('bower_components/simple-line-icons/fonts/Simple-Line-Icons.woff2',{
		destDir: 'fonts'
	});
	app.import('bower_components/simple-line-icons/fonts/Simple-Line-Icons.svg',{
		destDir: 'fonts'
	});

  return app.toTree();
};