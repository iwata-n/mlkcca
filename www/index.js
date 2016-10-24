const milkcocoa = new MilkCocoa('zooiunofuth.mlkcca.com');
const ds = milkcocoa.dataStore('messages');

function send() {
    ds.send({title: 'send', content: 'hoge'});
}

function push() {
    ds.push({title: 'push', content: 'fuga'});
}

ds.on('send', (sended) => {
  console.log('sendされました！titleは'+sended.value.title+' contentは'+sended.value.content+'です！');
});

ds.on('push', (sended) => {
  console.log('pushされました！titleは'+sended.value.title+' contentは'+sended.value.content+'です！');
});