//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:6.0.14
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using NMF.Collections.Generic;
using NMF.Collections.ObjectModel;
using NMF.Expressions;
using NMF.Expressions.Linq;
using NMF.Models;
using NMF.Models.Collections;
using NMF.Models.Expressions;
using NMF.Models.Meta;
using NMF.Models.Repository;
using NMF.Serialization;
using NMF.Utilities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Collections.Specialized;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;

namespace GeneratedCode.Miniyaml
{
    
    
    /// <summary>
    /// The default implementation of the MapEntry class
    /// </summary>
    [XmlNamespaceAttribute("http://york.ac.uk/ttc/miniyaml/1.0.0")]
    [XmlNamespacePrefixAttribute("c")]
    [ModelRepresentationClassAttribute("http://york.ac.uk/ttc/miniyaml/1.0.0#//MapEntry")]
    public partial class MapEntry : ModelElement, IMapEntry, IModelElement
    {
        
        /// <summary>
        /// The backing field for the Key property
        /// </summary>
        [DebuggerBrowsableAttribute(DebuggerBrowsableState.Never)]
        private string _key;
        
        private static Lazy<ITypedElement> _keyAttribute = new Lazy<ITypedElement>(RetrieveKeyAttribute);
        
        private static Lazy<ITypedElement> _valueReference = new Lazy<ITypedElement>(RetrieveValueReference);
        
        /// <summary>
        /// The backing field for the Value property
        /// </summary>
        [DebuggerBrowsableAttribute(DebuggerBrowsableState.Never)]
        private IValue _value;
        
        private static IClass _classInstance;
        
        /// <summary>
        /// The key property
        /// </summary>
        [DisplayNameAttribute("key")]
        [CategoryAttribute("MapEntry")]
        [XmlElementNameAttribute("key")]
        [XmlAttributeAttribute(true)]
        public string Key
        {
            get
            {
                return this._key;
            }
            set
            {
                if ((this._key != value))
                {
                    string old = this._key;
                    ValueChangedEventArgs e = new ValueChangedEventArgs(old, value);
                    this.OnKeyChanging(e);
                    this.OnPropertyChanging("Key", e, _keyAttribute);
                    this._key = value;
                    this.OnKeyChanged(e);
                    this.OnPropertyChanged("Key", e, _keyAttribute);
                }
            }
        }
        
        /// <summary>
        /// The value property
        /// </summary>
        [BrowsableAttribute(false)]
        [XmlElementNameAttribute("value")]
        [XmlAttributeAttribute(false)]
        [ContainmentAttribute()]
        public IValue Value
        {
            get
            {
                return this._value;
            }
            set
            {
                if ((this._value != value))
                {
                    IValue old = this._value;
                    ValueChangedEventArgs e = new ValueChangedEventArgs(old, value);
                    this.OnValueChanging(e);
                    this.OnPropertyChanging("Value", e, _valueReference);
                    this._value = value;
                    if ((old != null) && (old.Parent == this))
                    {
                        old.Parent = null;
                        old.ParentChanged -= this.OnResetValue;
                    }
                    if ((value != null))
                    {
                        value.Parent = this;
                        value.ParentChanged += this.OnResetValue;
                    }
                    this.OnValueChanged(e);
                    this.OnPropertyChanged("Value", e, _valueReference);
                }
            }
        }
        
        /// <summary>
        /// Gets the child model elements of this model element
        /// </summary>
        public override IEnumerableExpression<IModelElement> Children
        {
            get
            {
                return base.Children.Concat(new MapEntryChildrenCollection(this));
            }
        }
        
        /// <summary>
        /// Gets the referenced model elements of this model element
        /// </summary>
        public override IEnumerableExpression<IModelElement> ReferencedElements
        {
            get
            {
                return base.ReferencedElements.Concat(new MapEntryReferencedElementsCollection(this));
            }
        }
        
        /// <summary>
        /// Gets the Class model for this type
        /// </summary>
        public new static IClass ClassInstance
        {
            get
            {
                if ((_classInstance == null))
                {
                    _classInstance = ((IClass)(MetaRepository.Instance.Resolve("http://york.ac.uk/ttc/miniyaml/1.0.0#//MapEntry")));
                }
                return _classInstance;
            }
        }
        
        /// <summary>
        /// Gets fired before the Key property changes its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> KeyChanging;
        
        /// <summary>
        /// Gets fired when the Key property changed its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> KeyChanged;
        
        /// <summary>
        /// Gets fired before the Value property changes its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> ValueChanging;
        
        /// <summary>
        /// Gets fired when the Value property changed its value
        /// </summary>
        public event System.EventHandler<ValueChangedEventArgs> ValueChanged;
        
        private static ITypedElement RetrieveKeyAttribute()
        {
            return ((ITypedElement)(((ModelElement)(GeneratedCode.Miniyaml.MapEntry.ClassInstance)).Resolve("key")));
        }
        
        /// <summary>
        /// Raises the KeyChanging event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnKeyChanging(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.KeyChanging;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Raises the KeyChanged event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnKeyChanged(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.KeyChanged;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        private static ITypedElement RetrieveValueReference()
        {
            return ((ITypedElement)(((ModelElement)(GeneratedCode.Miniyaml.MapEntry.ClassInstance)).Resolve("value")));
        }
        
        /// <summary>
        /// Raises the ValueChanging event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnValueChanging(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.ValueChanging;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Raises the ValueChanged event
        /// </summary>
        /// <param name="eventArgs">The event data</param>
        protected virtual void OnValueChanged(ValueChangedEventArgs eventArgs)
        {
            System.EventHandler<ValueChangedEventArgs> handler = this.ValueChanged;
            if ((handler != null))
            {
                handler.Invoke(this, eventArgs);
            }
        }
        
        /// <summary>
        /// Handles the event that the Value property must reset
        /// </summary>
        /// <param name="sender">The object that sent this reset request</param>
        /// <param name="eventArgs">The event data for the reset event</param>
        private void OnResetValue(object sender, System.EventArgs eventArgs)
        {
            this.Value = null;
        }
        
        /// <summary>
        /// Gets the relative URI fragment for the given child model element
        /// </summary>
        /// <returns>A fragment of the relative URI</returns>
        /// <param name="element">The element that should be looked for</param>
        protected override string GetRelativePathForNonIdentifiedChild(IModelElement element)
        {
            if ((element == this.Value))
            {
                return ModelHelper.CreatePath("Value");
            }
            return base.GetRelativePathForNonIdentifiedChild(element);
        }
        
        /// <summary>
        /// Resolves the given URI to a child model element
        /// </summary>
        /// <returns>The model element or null if it could not be found</returns>
        /// <param name="reference">The requested reference name</param>
        /// <param name="index">The index of this reference</param>
        protected override IModelElement GetModelElementForReference(string reference, int index)
        {
            if ((reference == "VALUE"))
            {
                return this.Value;
            }
            return base.GetModelElementForReference(reference, index);
        }
        
        /// <summary>
        /// Resolves the given attribute name
        /// </summary>
        /// <returns>The attribute value or null if it could not be found</returns>
        /// <param name="attribute">The requested attribute name</param>
        /// <param name="index">The index of this attribute</param>
        protected override object GetAttributeValue(string attribute, int index)
        {
            if ((attribute == "KEY"))
            {
                return this.Key;
            }
            return base.GetAttributeValue(attribute, index);
        }
        
        /// <summary>
        /// Sets a value to the given feature
        /// </summary>
        /// <param name="feature">The requested feature</param>
        /// <param name="value">The value that should be set to that feature</param>
        protected override void SetFeature(string feature, object value)
        {
            if ((feature == "VALUE"))
            {
                this.Value = ((IValue)(value));
                return;
            }
            if ((feature == "KEY"))
            {
                this.Key = ((string)(value));
                return;
            }
            base.SetFeature(feature, value);
        }
        
        /// <summary>
        /// Gets the property expression for the given attribute
        /// </summary>
        /// <returns>An incremental property expression</returns>
        /// <param name="attribute">The requested attribute in upper case</param>
        protected override NMF.Expressions.INotifyExpression<object> GetExpressionForAttribute(string attribute)
        {
            if ((attribute == "KEY"))
            {
                return new KeyProxy(this);
            }
            return base.GetExpressionForAttribute(attribute);
        }
        
        /// <summary>
        /// Gets the property expression for the given reference
        /// </summary>
        /// <returns>An incremental property expression</returns>
        /// <param name="reference">The requested reference in upper case</param>
        protected override NMF.Expressions.INotifyExpression<NMF.Models.IModelElement> GetExpressionForReference(string reference)
        {
            if ((reference == "VALUE"))
            {
                return new ValueProxy(this);
            }
            return base.GetExpressionForReference(reference);
        }
        
        /// <summary>
        /// Gets the Class for this model element
        /// </summary>
        public override IClass GetClass()
        {
            if ((_classInstance == null))
            {
                _classInstance = ((IClass)(MetaRepository.Instance.Resolve("http://york.ac.uk/ttc/miniyaml/1.0.0#//MapEntry")));
            }
            return _classInstance;
        }
        
        /// <summary>
        /// The collection class to to represent the children of the MapEntry class
        /// </summary>
        public class MapEntryChildrenCollection : ReferenceCollection, ICollectionExpression<IModelElement>, ICollection<IModelElement>
        {
            
            private MapEntry _parent;
            
            /// <summary>
            /// Creates a new instance
            /// </summary>
            public MapEntryChildrenCollection(MapEntry parent)
            {
                this._parent = parent;
            }
            
            /// <summary>
            /// Gets the amount of elements contained in this collection
            /// </summary>
            public override int Count
            {
                get
                {
                    int count = 0;
                    if ((this._parent.Value != null))
                    {
                        count = (count + 1);
                    }
                    return count;
                }
            }
            
            protected override void AttachCore()
            {
                this._parent.ValueChanged += this.PropagateValueChanges;
            }
            
            protected override void DetachCore()
            {
                this._parent.ValueChanged -= this.PropagateValueChanges;
            }
            
            /// <summary>
            /// Adds the given element to the collection
            /// </summary>
            /// <param name="item">The item to add</param>
            public override void Add(IModelElement item)
            {
                if ((this._parent.Value == null))
                {
                    IValue valueCasted = item.As<IValue>();
                    if ((valueCasted != null))
                    {
                        this._parent.Value = valueCasted;
                        return;
                    }
                }
            }
            
            /// <summary>
            /// Clears the collection and resets all references that implement it.
            /// </summary>
            public override void Clear()
            {
                this._parent.Value = null;
            }
            
            /// <summary>
            /// Gets a value indicating whether the given element is contained in the collection
            /// </summary>
            /// <returns>True, if it is contained, otherwise False</returns>
            /// <param name="item">The item that should be looked out for</param>
            public override bool Contains(IModelElement item)
            {
                if ((item == this._parent.Value))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Copies the contents of the collection to the given array starting from the given array index
            /// </summary>
            /// <param name="array">The array in which the elements should be copied</param>
            /// <param name="arrayIndex">The starting index</param>
            public override void CopyTo(IModelElement[] array, int arrayIndex)
            {
                if ((this._parent.Value != null))
                {
                    array[arrayIndex] = this._parent.Value;
                    arrayIndex = (arrayIndex + 1);
                }
            }
            
            /// <summary>
            /// Removes the given item from the collection
            /// </summary>
            /// <returns>True, if the item was removed, otherwise False</returns>
            /// <param name="item">The item that should be removed</param>
            public override bool Remove(IModelElement item)
            {
                if ((this._parent.Value == item))
                {
                    this._parent.Value = null;
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Gets an enumerator that enumerates the collection
            /// </summary>
            /// <returns>A generic enumerator</returns>
            public override IEnumerator<IModelElement> GetEnumerator()
            {
                return Enumerable.Empty<IModelElement>().Concat(this._parent.Value).GetEnumerator();
            }
        }
        
        /// <summary>
        /// The collection class to to represent the children of the MapEntry class
        /// </summary>
        public class MapEntryReferencedElementsCollection : ReferenceCollection, ICollectionExpression<IModelElement>, ICollection<IModelElement>
        {
            
            private MapEntry _parent;
            
            /// <summary>
            /// Creates a new instance
            /// </summary>
            public MapEntryReferencedElementsCollection(MapEntry parent)
            {
                this._parent = parent;
            }
            
            /// <summary>
            /// Gets the amount of elements contained in this collection
            /// </summary>
            public override int Count
            {
                get
                {
                    int count = 0;
                    if ((this._parent.Value != null))
                    {
                        count = (count + 1);
                    }
                    return count;
                }
            }
            
            protected override void AttachCore()
            {
                this._parent.ValueChanged += this.PropagateValueChanges;
            }
            
            protected override void DetachCore()
            {
                this._parent.ValueChanged -= this.PropagateValueChanges;
            }
            
            /// <summary>
            /// Adds the given element to the collection
            /// </summary>
            /// <param name="item">The item to add</param>
            public override void Add(IModelElement item)
            {
                if ((this._parent.Value == null))
                {
                    IValue valueCasted = item.As<IValue>();
                    if ((valueCasted != null))
                    {
                        this._parent.Value = valueCasted;
                        return;
                    }
                }
            }
            
            /// <summary>
            /// Clears the collection and resets all references that implement it.
            /// </summary>
            public override void Clear()
            {
                this._parent.Value = null;
            }
            
            /// <summary>
            /// Gets a value indicating whether the given element is contained in the collection
            /// </summary>
            /// <returns>True, if it is contained, otherwise False</returns>
            /// <param name="item">The item that should be looked out for</param>
            public override bool Contains(IModelElement item)
            {
                if ((item == this._parent.Value))
                {
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Copies the contents of the collection to the given array starting from the given array index
            /// </summary>
            /// <param name="array">The array in which the elements should be copied</param>
            /// <param name="arrayIndex">The starting index</param>
            public override void CopyTo(IModelElement[] array, int arrayIndex)
            {
                if ((this._parent.Value != null))
                {
                    array[arrayIndex] = this._parent.Value;
                    arrayIndex = (arrayIndex + 1);
                }
            }
            
            /// <summary>
            /// Removes the given item from the collection
            /// </summary>
            /// <returns>True, if the item was removed, otherwise False</returns>
            /// <param name="item">The item that should be removed</param>
            public override bool Remove(IModelElement item)
            {
                if ((this._parent.Value == item))
                {
                    this._parent.Value = null;
                    return true;
                }
                return false;
            }
            
            /// <summary>
            /// Gets an enumerator that enumerates the collection
            /// </summary>
            /// <returns>A generic enumerator</returns>
            public override IEnumerator<IModelElement> GetEnumerator()
            {
                return Enumerable.Empty<IModelElement>().Concat(this._parent.Value).GetEnumerator();
            }
        }
        
        /// <summary>
        /// Represents a proxy to represent an incremental access to the key property
        /// </summary>
        private sealed class KeyProxy : ModelPropertyChange<IMapEntry, string>
        {
            
            /// <summary>
            /// Creates a new observable property access proxy
            /// </summary>
            /// <param name="modelElement">The model instance element for which to create the property access proxy</param>
            public KeyProxy(IMapEntry modelElement) : 
                    base(modelElement, "key")
            {
            }
            
            /// <summary>
            /// Gets or sets the value of this expression
            /// </summary>
            public override string Value
            {
                get
                {
                    return this.ModelElement.Key;
                }
                set
                {
                    this.ModelElement.Key = value;
                }
            }
        }
        
        /// <summary>
        /// Represents a proxy to represent an incremental access to the value property
        /// </summary>
        private sealed class ValueProxy : ModelPropertyChange<IMapEntry, IValue>
        {
            
            /// <summary>
            /// Creates a new observable property access proxy
            /// </summary>
            /// <param name="modelElement">The model instance element for which to create the property access proxy</param>
            public ValueProxy(IMapEntry modelElement) : 
                    base(modelElement, "value")
            {
            }
            
            /// <summary>
            /// Gets or sets the value of this expression
            /// </summary>
            public override IValue Value
            {
                get
                {
                    return this.ModelElement.Value;
                }
                set
                {
                    this.ModelElement.Value = value;
                }
            }
        }
    }
}

