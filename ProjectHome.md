Restless is an open source Java library that provides programatic API access to various REST-based services.

Currently, Restless provides API support for the following platforms:
**The (now unavailable and undocumented) Box.net service.** Very preliminary support for EVDB

Support for other services will be provided in the future

Box.net API sample:

IBoxService box = new BoxService();
AuthorizationResult login = box.login('example@example.com', 'passwd');
FileListingResult listing = box.list('folderId', 'numOfNodesDeep');
List

&lt;BoxFile&gt;

 files = listing.getFolder().getFiles();
foreach (BoxFile file : files) {
> ...
}