var AWS = require('aws-sdk');
var DOC = require('dynamodb-doc');
var dynamo = new DOC.DynamoDB();

exports.handler = function(event, context) {
   var cb = function(err, data) {
      if(err) {
         console.log('error on GetInfo: ',err);
         context.done('Unable to retrieve  information', null);
      } else {
         if(data.Item && data.Item.user) {
             context.done(null, data.Item.user);
         } else {
              context.done(null, {"end":0});
         }
      }
   };
   
   
   dynamo.getItem({TableName:"User", Key:{ID:10}}, cb);
};
