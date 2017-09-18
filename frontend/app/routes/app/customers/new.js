import Ember from 'ember';
import AuthenticatedRouteMixin from 'ember-simple-auth/mixins/authenticated-route-mixin';

export default Ember.Route.extend(AuthenticatedRouteMixin, {
  titleToken: 'Cadastrar',
  breadCrumb: {
    title: 'Cadastrar'
  },
  model() {
    return this.store.createRecord('customer', {
      address: this.store.createRecord('customer_address'),
      phones: [this.store.createRecord('customer_phone')]
    });
  },
  actions: {
    willTransition() {
      const record = this.controller.get('model');
      if (record.get('isNew')) {
        return this.store.unloadRecord(record);
      }
    }
  }
});
