import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
	model() {
		return this.store.findAll('customer');
	},
  actions: {
    destroy(record) {
      record.destroyRecord()
        .then(() => {
          this.transitionTo('app.customers.index');
        });
    },
  }
});
