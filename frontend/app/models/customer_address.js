import DS from 'ember-data';

const { attr, belongsTo } = DS;

export default DS.Model.extend({
  street: attr('string'),
  number: attr('string'),
  complement: attr('string'),
  neighborhood: attr('string'),
  zipCode: attr('string'),
  city: attr('string'),
  state: attr('string'),
  country: attr('string'),
  customer: belongsTo('customer'),
  stateSelect: [{"id":"AC","label":"Acre"},{"id":"AL","label":"Alagoas"},{"id":"AM","label":"Amazonas"},{"id":"AP","label":"Amapá"},{"id":"BA","label":"Bahia"},{"id":"CE","label":"Ceará"},{"id":"DF","label":"Distrito Federal"},{"id":"ES","label":"Espírito Santo"},{"id":"GO","label":"Goiás"},{"id":"MA","label":"Maranhão"},{"id":"MG","label":"Minas Gerais"},{"id":"MS","label":"Mato Grosso do Sul"},{"id":"MT","label":"Mato Grosso"},{"id":"PA","label":"Pará"},{"id":"PB","label":"Paraíba"},{"id":"PE","label":"Pernambuco"},{"id":"PI","label":"Piauí"},{"id":"PR","label":"Paraná"},{"id":"RJ","label":"Rio de Janeiro"},{"id":"RN","label":"Rio Grande do Norte"},{"id":"RO","label":"Rondônia"},{"id":"RR","label":"Roraima"},{"id":"RS","label":"Rio Grande do Sul"},{"id":"SC","label":"Santa Catarina"},{"id":"SE","label":"Sergipe"},{"id":"SP","label":"São Paulo"},{"id":"TO","label":"Tocantins"}]
});
