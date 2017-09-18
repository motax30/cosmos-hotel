import DS from 'ember-data';

export default DS.Transform.extend({
  deserialize: function(serialized) {
    let day = serialized.date.day;
    let month = serialized.date.month;
    let year = serialized.date.year;

    let hour = serialized.time.hour;
    let minute = serialized.time.minute;
    let second = serialized.time.second;

    let datetime = new Date(year, month - 1, day, hour, minute, second);

    return datetime.toLocaleString('pt-br');
  },
  serialize: function() {
    return ""
  }
});
