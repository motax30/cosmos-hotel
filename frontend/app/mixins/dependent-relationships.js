import Ember from 'ember';

export default Ember.Mixin.create({
  save: function() {
    let recordsToDelete = [];
    this.eachRelationship((name, descriptor) => {
      if (descriptor.kind === 'hasMany') {
        this.get(name).forEach((object) => {
          if (object.get('isNew')) {
            recordsToDelete.push(object);
          }
        });
      }
    });

    let promise = this._super(...arguments);
    promise.then(() => {
      recordsToDelete.forEach((record) => {
        record.deleteRecord();
      });
    });
    return promise;
  }
});
