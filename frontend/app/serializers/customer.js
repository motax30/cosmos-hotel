import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
  attrs: {
    address: { embedded: 'always' },
    phones: { embedded: 'always' },
    createdAt: { serialize: false },
    updatedAt: { serialize: false }
  }
});
