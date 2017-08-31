import Ember from 'ember';

export default Ember.Component.extend({
	click: function(event) {
		if (event.target.className.indexOf('nav-dropdown-toggle') != -1) {
			Ember.$(event.target.parentNode).toggleClass('open');
		}
	}
});