import DS from 'ember-data';
import DependentRelationships from '../mixins/dependent-relationships';

const { attr, hasMany } = DS;

export default DS.Model.extend(DependentRelationships, {
  name: attr('string'),
  cpf_number: attr('string'),
  notes: attr('string'),
  birthday: attr('date'),
  address: hasMany('customer_address', { async: false, cascadeDelete: true })
});
