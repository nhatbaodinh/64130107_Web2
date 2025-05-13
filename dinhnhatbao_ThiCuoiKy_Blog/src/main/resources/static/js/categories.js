document.addEventListener('DOMContentLoaded', function() {
// Add category
document.getElementById('addCategoryBtn').addEventListener('click', function() {
    const name = document.getElementById('categoryName').value.trim();
    if (name) {
        fetch('/categories/new?name=' + encodeURIComponent(name), { method: 'POST' })
            .then(response => response.ok ? location.reload() : alert('Danh mục đã tồn tại'))
            .catch(() => alert('Có lỗi xảy ra'));
    }
});

// Edit category
document.querySelectorAll('button[data-bs-target="#editCategoryModal"]').forEach(button => {
button.addEventListener('click', function() {
document.getElementById('editCategoryId').value = this.getAttribute('data-id');
document.getElementById('editCategoryName').value = this.getAttribute('data-name');
});
});
document.getElementById('editCategoryBtn').addEventListener('click', function() {
const id = document.getElementById('editCategoryId').value;
const name = document.getElementById('editCategoryName').value.trim();
if (name) {
fetch(`/categories/update?id=${id}&name=${encodeURIComponent(name)}`, { method: 'POST' })
.then(response => response.ok ? location.reload() : alert('Danh mục đã tồn tại'))
.catch(() => alert('Có lỗi xảy ra'));
}
});

// Delete category
document.querySelectorAll('button[data-bs-target="#deleteCategoryModal"]').forEach(button => {
button.addEventListener('click', function() {
document.getElementById('deleteCategoryName').innerText = this.getAttribute('data-name');
document.getElementById('deleteCategoryBtn').setAttribute('data-id', this.getAttribute('data-id'));
});
});
document.getElementById('deleteCategoryBtn').addEventListener('click', function () {
const id = this.getAttribute('data-id');
const form = document.createElement('form');
form.method = 'POST';
form.action = `/categories/delete/${id}`;
document.body.appendChild(form);
form.submit();
});
});