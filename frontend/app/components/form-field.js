import Ember from 'ember';
import FormField from 'ember-form-for/components/form-field';

const { get, getWithDefault, set } = Ember;

export default FormField.extend({
  updateOnFocusOut: true,

  _processUpdate(object, propertyName, value) {
    let rawValue = get(this, 'rawValue');
    let deserializeValue = getWithDefault(this, 'deserializeValue', (value) => value);
    get(this, 'update')(object, propertyName, deserializeValue(value, rawValue));
  },

  actions: {
    processUpdate(object, propertyName, value) {
      if (!get(this, 'updateOnFocusOut')) {
        this._processUpdate(object, propertyName, value);
      } else {
        set(this, 'value', value);
      }
    },

    processFocusOut(object, propertyName, value) {
      if (get(this, 'updateOnFocusOut')) {
        this._processUpdate(object, propertyName, value);
      }
    }
  }
});
