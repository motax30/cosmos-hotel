import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
	titleToken: 'Cadastrar',
	breadCrumb: {
		title: 'Cadastrar'
	},
	model() {
		return this.store.createRecord('customer');
	}
});