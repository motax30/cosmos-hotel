import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    destroy(record) {
      record.destroyRecord()
        .then(() => {
          this.set('customer_destroy', false);
          this.transitionTo('app.customers.index');
        });
    }
  }
});
