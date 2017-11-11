import Ember from 'ember';
import ReceptionistValidations from '../../validations/receptionist';
import lookupValidator from 'ember-changeset-validations';
import Changeset from 'ember-changeset';

export default Ember.Component.extend({
  store: Ember.inject.service(),
  toast: Ember.inject.service(),
  init() {
    this._super(...arguments);
    let model = this.get('model');
    this.receptionist = new Changeset(model, lookupValidator(ReceptionistValidations), ReceptionistValidations);
  },
  actions: {
    submit: function (receptionist) {
      this.get('receptionist').validate();   

      this.$("input[invalid]").first().focus();

      if (!this.get('receptionist').get('isValid')) {
        return false;
      }

      /* Only in case of has many */
      this.sendAction('action', receptionist);
    }
  }
});
