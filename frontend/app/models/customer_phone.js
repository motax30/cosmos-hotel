import DS from 'ember-data';

const { attr, belongsTo } = DS;

export default DS.Model.extend({
  type: attr('string'),
  number: attr('string'),
  customer: belongsTo('customer'),
  typeSelect: [ { id: 'CELLULAR', label: 'Celular'}, { id: 'HOME', label: 'Residencial'}, { id: 'WORK', label: 'Trabalho'} ]
});
