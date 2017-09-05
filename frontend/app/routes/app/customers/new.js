import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
	titleToken: 'Cadastrar',
	breadCrumb: {
		title: 'Cadastrar'
	},
	model() {
	  return this.store.createRecord('customer', { address: [this.store.createRecord('customer_address')] } );
	},
  actions: {
    willTransition() {
      const record = this.controller.get('model');
      if (record.get('isNew')) {
        console.log(record.address);
        return this.store.unloadRecord(record);
      }
    }
  }
});
