// import buildMessage from 'ember-changeset-validations/utils/validation-errors';
import Ember from 'ember';

export default function validateUniquenessCustomerCpf() {
  return (key, newValue, oldValue, changes, content) => {

    return new Ember.RSVP.Promise((resolve) => {
      let store = content.get('store');
      let id = content._internalModel.id;
      store.query('customer', {
        filter: {
          cpfNumber: newValue
        }
      }).then(function(customers) {
        let customer = customers.get('firstObject');
        if (customer && customer.id !== id) {
          resolve("CPF já existente.");
        } else {
          resolve(true);
        }
      });

    });
  };
}
