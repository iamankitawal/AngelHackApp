var AWS = require('aws-sdk');
var DOC = require('dynamodb-doc');
var dynamo = new DOC.DynamoDB();
exports.handler = function(event, context) {
    var item = { ID:event.id,
                 user: event.user || {}
            };

    var cb = function(err, data) {
        if(err) {
            console.log(err);
            context.fail('unable to update user at this time');
        } else {
            console.log(data);
                context.done(null, data);
        }
    };
    dynamo.putItem({TableName:"User", Item:item}, cb);
};
