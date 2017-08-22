import DS from "ember-data";

var ApplicationAdapter = DS.RESTAdapter.extend({
  namespace: 'api',
  host: 'http://localhost:3000',
  pathForType: function(type) {
    return type + '.json';
  }
});

export default ApplicationAdapter;