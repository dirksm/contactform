function getHash(email) {
	return md5(strtolower(trim(email)))
}