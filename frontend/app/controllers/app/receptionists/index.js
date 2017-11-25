import Ember from 'ember';

export default Ember.Controller.extend({
  actions: {
    destroy(record) {
      record.destroyRecord()
        .then(() => {
          this.set('receptionist_destroy', false);
          this.transitionTo('app.receptionists.index');
        });
    }
  }
});
